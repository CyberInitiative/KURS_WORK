package com.company.classes;

import java.util.Arrays;

public class CPU {
    public Core[] getCores() {
        return cores;
    }

    public void setCores(Core[] cores) {
        this.cores = cores;
    }

    Core [] cores;

    public CPU(final int coresNumber){
        this.cores = new Core[coresNumber];
        for(int i = 0; i < coresNumber; i++){
            this.cores[i] = new Core();
        }
    }

    @Override
    public String toString() {
        String result = "[";
        for (Core core: cores){
            result += core.getState() + ", ";
        }
        return result + ']';
    }
}
