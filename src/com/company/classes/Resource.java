package com.company.classes;

public class Resource {
    private boolean isIdle = true;
    private int coreNumber;

    public Resource(int number) {
        this.coreNumber = number;
    }

    public int getNumber() {
        return coreNumber;
    }

    public boolean isIdle(){
        return isIdle;
    }

    public void setIdle(boolean idle){
        isIdle = idle;
    }
}
