package com.faztcode;

class Main
{

    private Predictor predictor;

    public Main()
    {
        predictor = new Predictor();
    }

    private void exec()
    {
        predictor.rushHour();
        System.out.println("Allowed");
    }

    public static void main(String[] args)
    {
        new Main().exec();
    }

}
