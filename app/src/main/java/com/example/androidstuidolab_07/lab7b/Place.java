package com.example.androidstuidolab_07.lab7b;

public class Place {
    private int placeId;
    private String placeName;

    @Override
    public String toString() {
        return placeId+". "+placeName;
    }

    public Place(int placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }

    public Place() {
    }

    public Place(String placeName) {
        this.placeName = placeName;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
