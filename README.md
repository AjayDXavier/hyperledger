# 🚀 Hyperledger Fabric Java Chaincode – Asset Tracker

This project contains a smart contract written in Java for managing and tracking assets on a Hyperledger Fabric blockchain network. Developed as part of an internship assignment.

---

## 📦 Features

- Create an asset
- Read an asset
- Update asset details
- Delete an asset

---

## 🔐 Asset Fields

Each asset has the following fields:

- `dealerId`
- `msisdn`
- `mpin`
- `balance`
- `status`
- `transAmount`
- `transType`
- `remarks`

---

## 🛠 Requirements

- Java 17+
- Gradle
- Docker & Docker Desktop
- WSL2 (for Windows users)
- Hyperledger Fabric v2.5+
- Fabric binaries (from `install-fabric.sh`)
- Git (to clone `fabric-samples`)

---

## 🏗️ Project Structure

fabric-java-asset-tracker/
├── build.gradle
├── settings.gradle
├── README.md
└── src/
└── main/
└── java/
└── org/
└── example/
├── Asset.java
└── AssetContract.java


---

## ⚙️ Build & Deploy Instructions

### 1️⃣ Build the Chaincode JAR

```bash
./gradlew build
This generates a .jar file inside build/libs/.

2️⃣ Start Fabric Test Network
From inside the fabric-samples/test-network directory:

./network.sh up createChannel -ca

3️⃣ Deploy the Chaincode
Replace the path with your JAR location:

./network.sh deployCC -ccn assetcontract \
  -ccp ../fabric-java-asset-tracker/build/libs \
  -ccl java
🧪 Test Your Chaincode

🔁 Set Org1 CLI Environment

export PATH=${PWD}/../bin:$PATH
export FABRIC_CFG_PATH=$PWD/../config
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID=Org1MSP
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_ADDRESS=localhost:7051
➕ Invoke createAsset

peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com \
--tls --cafile "${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem" \
-C mychannel -n assetcontract \
--peerAddresses localhost:7051 --tlsRootCertFiles "${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt" \
--peerAddresses localhost:9051 --tlsRootCertFiles "${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt" \
-c '{"function":"createAsset","Args":["DLR001","9876543210","1234","5000","active","0","credit","init"]}'

🔍 Query the Asset

peer chaincode query -C mychannel -n assetcontract -c '{"Args":["readAsset","DLR001"]}'



