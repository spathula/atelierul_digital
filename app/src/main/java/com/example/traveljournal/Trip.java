package com.example.traveljournal;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip implements Parcelable {
    private String tripName, destination, price, rating, tripType, startDate, endDate;

    public Trip(String tripName, String destination, String price, String rating, String tripType, String startDate, String endDate) {
        this.tripName = tripName;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.tripType = tripType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Trip() {
        this.tripName = "Trip Name";
        this.destination = "Destination";
        this.price = "Price";
        this.rating = "Rating";
        this.tripType = "Trip Type";
        this.startDate = "Start Date";
        this.endDate = "End Date";
    }

    public String getTripName() {
        return tripName;
    }

    public String getDestination() {
        return destination;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public Trip(Parcel in) {
        this.tripName = in.readString();
        this.destination = in.readString();
        this.price = in.readString();
        this.rating = in.readString();
        this.tripType = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tripName);
        dest.writeString(this.destination);
        dest.writeString(this.price);
        dest.writeString(this.rating);
        dest.writeString(this.tripType);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripName='" + tripName + '\'' +
                ", destination='" + destination + '\'' +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", tripType='" + tripType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
