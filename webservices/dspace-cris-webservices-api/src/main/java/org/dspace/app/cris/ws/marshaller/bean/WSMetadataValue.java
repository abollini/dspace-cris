package org.dspace.app.cris.ws.marshaller.bean;

public class WSMetadataValue
{
    private String value;
    private int place;
    private String authority;
    private Integer share;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getPlace() {
        return place;
    }
    public void setPlace(int place) {
        this.place = place;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public Integer getShare() {
        return share;
    }
    public void setShare(Integer share) {
        this.share = share;
    }

}
