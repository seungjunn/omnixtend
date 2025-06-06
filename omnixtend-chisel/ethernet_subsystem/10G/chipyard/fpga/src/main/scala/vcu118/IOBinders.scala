package chipyard.fpga.vcu118

import chisel3._
import chisel3.experimental.{IO, DataMirror}

import freechips.rocketchip.diplomacy.{ResourceBinding, Resource, ResourceAddress, InModuleBody}
import freechips.rocketchip.subsystem.{BaseSubsystem}
import freechips.rocketchip.util.{HeterogeneousBag}
import freechips.rocketchip.tilelink.{TLBundle}

import sifive.blocks.devices.uart.{HasPeripheryUARTModuleImp}
import sifive.blocks.devices.spi.{HasPeripherySPI, HasPeripherySPIModuleImp, MMCDevice}

import chipyard.{CanHaveMasterTLMemPort, CanHaveMasterTLOXPort}
import chipyard.iobinders.{OverrideIOBinder, OverrideLazyIOBinder}
import sifive.fpgashells.devices.xilinx.ethernet._
import freechips.rocketchip.subsystem._
import chipyard.iobinders._
import testchipip._
import chipsalliance.rocketchip._
import freechips.rocketchip.amba.axi4._
import freechips.rocketchip.config.{Parameters}
import sifive.fpgashells.clocks._
import barstools.iocell.chisel._
import freechips.rocketchip.diplomacy.{ModuleValue}

class WithUARTIOPassthrough extends OverrideIOBinder({
  (system: HasPeripheryUARTModuleImp) => {
    val io_uart_pins_temp = system.uart.zipWithIndex.map { case (dio, i) => IO(dio.cloneType).suggestName(s"uart_$i") }
    (io_uart_pins_temp zip system.uart).map { case (io, sysio) =>
      io <> sysio
    }
    (io_uart_pins_temp, Nil)
  }
})

class WithSPIIOPassthrough  extends OverrideLazyIOBinder({
  (system: HasPeripherySPI) => {
    // attach resource to 1st SPI
    ResourceBinding {
      Resource(new MMCDevice(system.tlSpiNodes.head.device, 1), "reg").bind(ResourceAddress(0))
    }

    InModuleBody {
      system.asInstanceOf[BaseSubsystem].module match { case system: HasPeripherySPIModuleImp => {
        val io_spi_pins_temp = system.spi.zipWithIndex.map { case (dio, i) => IO(dio.cloneType).suggestName(s"spi_$i") }
        (io_spi_pins_temp zip system.spi).map { case (io, sysio) =>
          io <> sysio
        }
        (io_spi_pins_temp, Nil)
      } }
    }
  }
})

class WithTLIOPassthrough extends OverrideIOBinder({
  (system: CanHaveMasterTLMemPort) => {
    val io_tl_mem_pins_temp = IO(DataMirror.internal.chiselTypeClone[HeterogeneousBag[TLBundle]](system.mem_tl)).suggestName("tl_slave")
    io_tl_mem_pins_temp <> system.mem_tl
    (Seq(io_tl_mem_pins_temp), Nil)
  }
})

/*** Ethernet AXI-Lite***/
class WithAXIIOPassthrough extends OverrideIOBinder({
  (system: CanHaveMasterAXI4MMIOPort) => {
    val io_tl_mem_pins_temp = IO(DataMirror.internal.chiselTypeClone[HeterogeneousBag[AXI4Bundle]](system.mmio_axi4)).suggestName("axi_slave")
    io_tl_mem_pins_temp <> system.mmio_axi4
    (Seq(io_tl_mem_pins_temp), Nil)
  }
})

/*** OX Tilelink ***/
class WithOXIOPassthrough extends OverrideIOBinder({
  (system: CanHaveMasterTLOXPort) => {
    val io_tl_mem_pins_temp = IO(DataMirror.internal.chiselTypeClone[HeterogeneousBag[TLBundle]](system.ox_tl)).suggestName("ox_slave")
    io_tl_mem_pins_temp <> system.ox_tl
    (Seq(io_tl_mem_pins_temp), Nil)
  }
})