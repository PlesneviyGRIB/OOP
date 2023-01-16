package org.mailreport;

import java.util.HashMap;

public class Dao {
    private HashMap<String, String> previous;
    private HashMap<String, String> current;

    {
        previous = new HashMap<>();

        previous.put("https://websitebuilders.com", "<div/>");
        previous.put("https://www.facebook.com/Learn-the-Net-330002341216/", "<div/>");
        previous.put("ftp://aeneas.mit.edu/", "<div/>");
        previous.put("https://en.wikipedia.org/wiki/Internet#Terminology", "<div/>");

        current = new HashMap<>(previous);

        current.put("https://websitebuilders.com", "<span/>");
        current.put("https://websitebuilders.com/how-to/web-at-a-glance/url-examples/", "<div />");
        current.put("https://websitebuilders.com/how/", "<div />");
        current.remove("https://en.wikipedia.org/wiki/Internet#Terminology");
    }

    public HashMap<String, String> getPrevious() {
        return new HashMap<>(previous);
    }

    public HashMap<String, String> getCurrent() {
        return new HashMap<>(current);
    }
}
