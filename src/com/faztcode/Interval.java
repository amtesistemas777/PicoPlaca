package com.faztcode;

class Interval
{

    private double half;

    private double longitude;

    public Interval()
    {
        this(0, 0);
    }

    public Interval(double top)
    {
        this(0, top);
    }

    public Interval(Interval interval)
    {
        this(interval.bottom(), interval.top());
    }

    public Interval(double bottom, double top)
    {
        setBottom(bottom);
        setTop(top);
    }

    private void setBottom(double bottom)
    {
        longitude = top() - bottom;
        half = bottom + longitude / 2;
    }

    public double top()
    {
        return half + longitude / 2;
    }

    private void setTop(double top)
    {
        longitude = top - bottom();
        half = top - longitude / 2;
    }

    public double bottom()
    {
        return half - longitude / 2;
    }


    public boolean belongs(double value)
    {
        return bottom() <= value && value <= top();
    }

    public boolean equals(Interval interval)
    {
        return bottom() == interval.bottom()
                && top() == interval.top();
    }

}
