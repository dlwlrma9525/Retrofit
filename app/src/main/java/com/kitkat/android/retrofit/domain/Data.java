package com.kitkat.android.retrofit.domain;

public class Data {
    private SearchParkingInfoRealtime SearchParkingInfoRealtime;

    public SearchParkingInfoRealtime getSearchParkingInfoRealtime ()
    {
        return SearchParkingInfoRealtime;
    }

    public void setSearchParkingInfoRealtime (SearchParkingInfoRealtime SearchParkingInfoRealtime)
    {
        this.SearchParkingInfoRealtime = SearchParkingInfoRealtime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SearchParkingInfoRealtime = "+SearchParkingInfoRealtime+"]";
    }

}
