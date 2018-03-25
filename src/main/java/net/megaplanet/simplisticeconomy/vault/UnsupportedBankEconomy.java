package net.megaplanet.simplisticeconomy.vault;

import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.ArrayList;
import java.util.List;

public abstract class UnsupportedBankEconomy extends AbstractEconomy {

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public EconomyResponse createBank(String playerName, String paramString2) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse deleteBank(String paramString) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse bankBalance(String paramString) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse bankHas(String paramString, double paramDouble) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse bankWithdraw(String paramString, double paramDouble) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse bankDeposit(String paramString, double paramDouble) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse isBankOwner(String paramString1, String paramString2) {
        return createUnsupportedResponse();
    }

    @Override
    public EconomyResponse isBankMember(String paramString1, String paramString2) {
        return createUnsupportedResponse();
    }

    private EconomyResponse createUnsupportedResponse() {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "SimplisticEconomy does not support bank accounts");
    }

    @Override
    public List<String> getBanks() {
        return new ArrayList<>();
    }

}
