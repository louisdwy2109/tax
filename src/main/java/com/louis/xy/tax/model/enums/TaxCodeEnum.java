package com.louis.xy.tax.model.enums;

public enum TaxCodeEnum {
    TAX_CODE_1("1","Food and Beverage", Boolean.TRUE),
    TAX_CODE_2("2","Tobacco", Boolean.FALSE),
    TAX_CODE_3("3","Entertainment", Boolean.FALSE);

    private final String taxCode;
    private final String taxName;
    private final boolean refundable;

    TaxCodeEnum(String taxCode, String taxName, boolean refundable) {
        this.taxCode = taxCode;
        this.taxName = taxName;
        this.refundable = refundable;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getTaxName() {
        return taxName;
    }

    public boolean isRefundable(){
        return refundable;
    }

    public static TaxCodeEnum findByTaxCode(String taxCode){

        if(TAX_CODE_1.getTaxCode().equals(taxCode)){
            return TAX_CODE_1;
        }

        if(TAX_CODE_2.getTaxCode().equals(taxCode)){
            return TAX_CODE_2;
        }

        if (TAX_CODE_3.getTaxCode().equals(taxCode)){
            return TAX_CODE_3;
        }

        return TAX_CODE_1;
    }
}
