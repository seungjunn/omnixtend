# OmniXtend-C

OmniXtend-C is a C implementation of the OmniXtend protocol, which provides a reliable communication layer over Ethernet or Message Queue for TileLink protocol.

## Overview

This implementation provides:
- Reliable communication over Ethernet or Message Queue
- Flow control mechanism
- Retransmission handling
- Sequence number management
- Support for TileLink protocol messages

## Project Structure

```
omnixtend-c/
├── flowcontrol.c/h      - Flow control implementation
├── retransmission.c/h   - Retransmission handling
├── tilelink_handler.c/h - TileLink message handling
├── tilelink_msg.c/h     - TileLink message definitions
├── timeout.c/h          - Timeout handling
├── tloe_connection.c/h  - Connection management
├── tloe_endpoint.c/h    - Main endpoint implementation
├── tloe_ether.c/h       - Ethernet communication
├── tloe_fabric.c/h      - Fabric abstraction layer
├── tloe_frame.c/h       - Frame format and handling
├── tloe_mq.c/h          - Message Queue communication
├── tloe_ns.c            - Network simulator
├── tloe_ns_thread.c/h   - Network simulator thread
├── tloe_nsm.c/h         - Network simulator management
├── tloe_receiver.c/h    - Receiver implementation
├── tloe_seq_mgr.c/h     - Sequence number management
├── tloe_transmitter.c/h - Transmitter implementation
└── util/                - Utility functions
```

## Building

To build the project:

```bash
make
```

This will create two executables:
- `tloe_endpoint`: The main endpoint implementation
- `tloe_ns`: Network simulator for testing

### Build Options

The following options can be set during build:
- `DEBUG=1`: Enable debug output
- `TEST_NORMAL_FRAME_DROP=1`: Enable normal frame drop testing
- `TEST_TIMEOUT_DROP=1`: Enable timeout drop testing
- `MEMCHECK=1`: Enable memory checking
- `WDE=1`: Enable WD mode (only A, C, and E channels)

Example:
```bash
make DEBUG=1
```

## Usage

### Endpoint

The endpoint can be run in either master or slave mode, using either Ethernet or Message Queue:

```bash
# Ethernet mode
./tloe_endpoint -i <interface> -d <destination_mac> -m  # Master mode
./tloe_endpoint -i <interface> -d <destination_mac> -s  # Slave mode

# Message Queue mode
./tloe_endpoint -p <queue_name> -m  # Master mode
./tloe_endpoint -p <queue_name> -s  # Slave mode
```

### Network Simulator

The network simulator can be used to test the communication:

```bash
./tloe_ns -p <queue_name>
```

Available commands in the simulator:
- `s`: Toggle simulator (start/stop)
- `start/run/r`: Start simulator
- `stop/halt/h`: Stop simulator
- `flush/f`: Flush message queues
- `a <count>`: Drop next <count> requests from Port A to B
- `b <count>`: Drop next <count> requests from Port B to A
- `w <count>`: Drop next <count> requests bi-directionally
- `quit/q`: Quit program

## Features

- Reliable communication with retransmission
- Flow control with credit-based mechanism
- Support for TileLink protocol messages
- Multiple communication backends (Ethernet, Message Queue)
- Network simulator for testing
- Debug and testing options

## License

This project is licensed under the terms of the license included in the repository.