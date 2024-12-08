# GOST3411Hash

**GOST3411Hash** is a pure Java implementation of the **GOST R 34.11-94** hashing algorithm. It is designed for applications requiring secure message digests following the Russian cryptographic standard.

---

## Features

- **Pure Java Implementation**: No external libraries required.
- **Secure Hashing**: Implements the GOST R 34.11-94 standard.
- **Fixed-Length Hash**: Outputs a 256-bit (32-byte) hash.
- **Efficient and Lightweight**: Suitable for resource-constrained environments.

---

## Use Cases

- **Data Integrity**: Verify that data has not been altered.
- **Digital Signatures**: Generate message digests for secure signing.
- **Compliance**: Meet requirements for systems that mandate GOST standards.

---

## Installation

Copy the `GOST3411Hash` class into your Java project. No additional setup or dependencies are required.

---

## Usage

### Basic Example
```java
public class Main {
    public static void main(String[] args) {
        String input = "Hello, World!";
        
        String hash = GOST3411Hash.gostHash(input);
        System.out.println("GOST R 34.11-94 Hash: " + hash);
    }
}
