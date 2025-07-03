package org.example;

import com.owlike.genson.Genson;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract(name = "AssetContract")
@Default
public class AssetContract implements ContractInterface {

    private final Genson genson = new Genson();

    @Transaction()
    public void createAsset(Context ctx, String dealerId, String msisdn, String mpin, double balance,
                            String status, double transAmount, String transType, String remarks) {
        ChaincodeStub stub = ctx.getStub();
        String assetJSON = stub.getStringState(dealerId);
        if (assetJSON != null && !assetJSON.isEmpty()) {
            throw new ChaincodeException("Asset already exists");
        }

        Asset asset = new Asset(dealerId, msisdn, mpin, balance, status, transAmount, transType, remarks);
        stub.putStringState(dealerId, genson.serialize(asset));
    }

    @Transaction()
    public Asset readAsset(Context ctx, String dealerId) {
        ChaincodeStub stub = ctx.getStub();
        String assetJSON = stub.getStringState(dealerId);
        if (assetJSON == null || assetJSON.isEmpty()) {
            throw new ChaincodeException("Asset not found");
        }
        return genson.deserialize(assetJSON, Asset.class);
    }

    @Transaction()
    public void updateAsset(Context ctx, String dealerId, double balance, String status,
                            double transAmount, String transType, String remarks) {
        Asset asset = readAsset(ctx, dealerId);
        asset.balance = balance;
        asset.status = status;
        asset.transAmount = transAmount;
        asset.transType = transType;
        asset.remarks = remarks;

        ctx.getStub().putStringState(dealerId, genson.serialize(asset));
    }

    @Transaction()
    public void deleteAsset(Context ctx, String dealerId) {
        ctx.getStub().delState(dealerId);
    }
}
