package info.adavis.stickyeventsexample;

/**
 * @author Annyce Davis
 */
class LocationRetrievedEvent
{
    private final double longitude;
    private final double latitude;

    LocationRetrievedEvent (double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    double getLongitude ()
    {
        return longitude;
    }

    double getLatitude ()
    {
        return latitude;
    }
}
