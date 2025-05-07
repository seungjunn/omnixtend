<p align="center">
  <img src="docs/logo/omnixtend-logo.png" alt="OmniXtend Logo" width="300"/>
</p>

# OmniXtend Unified Repository

**OmniXtend** is an open, Ethernet-based memory coherence protocol that extends RISC-V’s memory model across distributed systems.  
This repository provides a unified, modular workspace for developing, testing, and deploying OmniXtend components using **C**, **Verilog RTL**, and **Chisel** implementations.

Each component is organized for compatibility with industry and research frameworks such as **TLEO** and **Chipyard**, enabling practical integration with host machines, memory nodes, and FPGA-based SoC systems.

## 📁 Repository Structure

```
omnixtend/
├── omnixtend-c/          # C-based TLoE implementation for both host and memory
├── omnixtend-verilog/    # Verilog RTL modules for Chipyard-compatible hardware integration
├── omnixtend-chisel/     # Chisel-based protocol components and SoC modules for Chipyard
├── tools/                # Utility applications for traffic generation, analysis, and debugging
├── examples/             # End-to-end system examples for simulation and FPGA deployment
├── docs/                 # Architecture notes, design documents, and OmniXtend standard specs
├── scripts/              # Common environment setup and build scripts
├── LICENSE               # Apache 2.0 License
└── README.md             # Project overview and instructions
```

## 🔧 Components

- **`omnixtend-c/`**
  - C-based implementation of the OmniXtend protocol following the *TLoE framework* specification.
  - Supports both **host** and **memory device** roles.
  - Useful for protocol validation, simulation environments, and interfacing with real hardware.

- **`omnixtend-verilog/`**
  - Verilog RTL implementation of OmniXtend protocol logic.
  - Designed to work within the **Chipyard** infrastructure for simulation and FPGA synthesis.
  - Provides low-level building blocks and testbenches for accurate hardware modeling.

- **`omnixtend-chisel/`**
  - Chisel-based high-level modules implementing OmniXtend protocol components.
  - Fully compatible with **Chipyard** and integrates with RISC-V SoC designs.
  - Includes bridges, transactors, and configurable interfaces for scalable design.

- **`tools/`**
  - Collection of software utilities and applications supporting the OmniXtend ecosystem.
  - Includes traffic generators, packet analyzers, debugging helpers, and performance testers.
  - Aims to simplify development, testing, and demonstration.

- **`examples/`**
  - Complete integration examples demonstrating real-world usage scenarios.
  - Combines components from `-c`, `-verilog`, and `-chisel` to create functional test platforms.
  - Targeted for FPGA deployments, end-to-end validation, and educational use.

- **`docs/`**
  - Documentation hub for the entire project.
  - Contains architecture overviews, technical notes, and protocol specification documents.
  - Includes the official *OmniXtend standard documents* used for reference and compliance.

## 🚀 Getting Started

## 📚 References

- [Chipyard SoC Generator](https://chipyard.readthedocs.io)

## 🤝 Contribution

We welcome contributions from anyone interested in:

- RISC-V memory coherence  
- Ethernet-based memory systems  
- High-performance SoC integration  

Please feel free to open issues or submit pull requests.

## 📜 License

This project is licensed under the Apache License 2.0. See the LICENSE file for details.
