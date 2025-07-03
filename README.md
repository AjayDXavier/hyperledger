# Hyperledger Fabric Java Chaincode - Asset Tracker

This project contains a smart contract written in Java for managing and tracking assets on a Hyperledger Fabric blockchain network. Developed as part of an internship assignment.

## 📦 Features

- Create an asset
- Read an asset
- Update asset details
- Delete an asset

## 🔐 Asset Properties

Each asset includes:

- `dealerId`
- `msisdn`
- `mpin`
- `balance`
- `status`
- `transAmount`
- `transType`
- `remarks`

## 🛠 Requirements

- Java 17+
- Gradle
- Docker Desktop
- WSL2 or Linux environment
- Hyperledger Fabric v2.5+

## 🚀 Build Instructions

To build the JAR file:

```bash
./gradlew build
