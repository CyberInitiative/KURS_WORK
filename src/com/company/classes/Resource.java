package com.company.classes;

public class  Resource {
    private boolean isIdle = true;
    private int resourceNumber;

    public Resource(int number) {
        this.resourceNumber = number;
    }

    public int getNumber() {
        return resourceNumber;
    }

    public boolean isIdle(){
        return isIdle;
    }

    public void setIdle(boolean idle){
        isIdle = idle;
    }
}
