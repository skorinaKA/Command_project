package com.company.bookstore.web.screens.store;


import com.company.bookstore.entity.Store;
import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.components.LayoutClickNotifier;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolygon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@UiController("bookstore_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {

    @Inject
    private HBoxLayout Maps;

    @Inject
    private TextField<String> addressField;

    @Inject
    private Logger logger;

    private HttpURLConnection connection = null;
    private GoogleMap googleMap = new GoogleMap(null, null, "english");
    private GoogleMapInfoWindow window = new GoogleMapInfoWindow();
    private String coordinates;
    private LatLon latLon;

    @Subscribe
    protected void onInit(InitEvent event){
        Maps.unwrap(Layout.class).addComponent(googleMap);
        googleMap.setSizeFull();
        googleMap.setCenter(new LatLon(59.9386,30.3141));
        googleMap.setZoom(12);

/////////////////
//        googleMap.addMarker("DRAGGABLE: Paavo Nurmi Stadion", new LatLon(
//                60.442423, 22.26044), true, "VAADIN/1377279006_stadium.png");
//        googleMap.addMarker("DRAGGABLE: Kakolan vankila",
//                new LatLon(60.44291, 22.242415), true, null);
//        googleMap.addMarker("NOT DRAGGABLE: Iso-Heikkilä", new LatLon(
//                60.450403, 22.230399), false, null);
//        googleMap.setMinZoom(4);
//        googleMap.setMaxZoom(16);
//
//        ArrayList<LatLon> points = new ArrayList<LatLon>();
//        points.add(new LatLon(60.484715, 21.923706));
//        points.add(new LatLon(60.446636, 21.941387));
//        points.add(new LatLon(60.422496, 21.99546));
//        points.add(new LatLon(60.427326, 22.06464));
//        points.add(new LatLon(60.446467, 22.064297));
//        GoogleMapPolygon overlay = new GoogleMapPolygon(points,
//                "#ae1f1f", 0.8, "#194915", 0.5, 3);
//        googleMap.addPolygonOverlay(overlay);
//
//        points = new ArrayList<LatLon>();
//        points.add(new LatLon(60.448118, 22.253738));
//        points.add(new LatLon(60.455144, 22.24198));
//        points.add(new LatLon(60.460222, 22.211939));
//        points.add(new LatLon(60.488224, 22.174602));
//        points.add(new LatLon(60.486025, 22.169195));
//        GoogleMapPolyline overlay1 = new GoogleMapPolyline(
//                points, "#d31717", 0.8, 10);
//        googleMap.addPolyline(overlay1);
//
//
//        googleMap.setInfoWindowContents(window, new Button("A Vaadin button!"));
//        googleMap.openInfoWindow(window);
///////////////////////////

        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon position) {
                latLon = position;
                coordinates = position.getLat() + "," + position.getLon();
//                addressField.setValue("Map click to (" + coordinates + ")");


                try {
                    URL url = new URL("https://geocode.maps.co/reverse?lat="+latLon.getLat() +
                            "&lon="+latLon.getLon());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    connection.setDoOutput(true);

                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                    wr.close();

                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line=rd.readLine())!=null){
                        response.append(line);
                        response.append('\r');
                    }
                    rd.close();
                    JSONObject location = new JSONObject(response.toString());
                    logger.info(location.getString("display_name"));
                    addressField.setValue(location.getString("display_name"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Subscribe("Maps")
    public void onMapsLayoutClick(LayoutClickNotifier.LayoutClickEvent event) {
    }


    @Subscribe
    protected void onInitEntity(InitEntityEvent<Store> event) {
        event.getEntity().setAddress("Street of some Google-maps");
    }

}