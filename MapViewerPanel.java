package MapProject;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import java.awt.*;

public class MapViewerPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JMapViewer map;
    private LegendPanel legend;
    public static final Font LABEL_FONT = new Font("Times New Roman", Font.BOLD, 13);

    public MapViewerPanel(JFrame frame) {
        super(null);
        map = new JMapViewer();

        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);

        legend = new LegendPanel();
        JLayeredPane layeredPane = frame.getLayeredPane();
        legend.setBounds(frame.getWidth() - 200, 10, 190, 160);
        legend.setVisible(true);
        layeredPane.add(legend, JLayeredPane.PALETTE_LAYER);

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                legend.setBounds(frame.getWidth() - 200, 10, 190, 160);
            }
        });
    }

    public void loadMap() {
        // Hardcoded location: Cebu City
        double lat = 10.3157;
        double lon = 123.8854;
        String label = "Cebu City";

        Coordinate coord = new Coordinate(lat, lon);
        map.setDisplayPosition(coord, 12);
        map.removeAllMapMarkers();

        map.addMapMarker(new YouDot(label, lat, lon, 10));

        Color USC = new Color(80, 200, 120);
        map.addMapMarker(new LabeledDot("USC Talamban Campus", 10.3521, 123.9133, USC, 10));

        map.addMapMarker(new LabeledDot("SIP CAFE CORPORATION", 10.318716, 123.891025, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("SIP CAFE CORPORATION", 10.300432, 123.898012, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("INFUSO COFFEE OUTLET", 10.310140, 123.896033, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("The Mezzanine Cafe", 10.30710772, 123.899673, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("But First, Coffee", 10.33056, 123.90722, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("Cafe Francois", 10.3174464, 123.9020488, Color.ORANGE, 6));

        Color GOLDENROD = new Color(218, 165, 32);
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.323998, 123.978118, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.304238, 123.911664, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.318818, 123.904939, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.266569, 123.878339, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.329305, 123.902295, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.281832, 123.881430, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.380558, 123.964611, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.309161, 123.962611, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Brique Modern Kitchen", 10.304214, 123.911684, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Brique Modern Kitchen", 10.331186, 123.906812, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Brique Modern Kitchen", 10.282416, 123.880084, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Brique Modern Kitchen", 10.309867, 123.875873, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Sutukil by the Bay", 10.283087, 123.880694, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Parola Seaview Restaurant", 10.242896, 123.955859, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Da-In Korean Restaurant", 10.329498, 123.902323, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Ku-Bu Restaurant + Bar", 10.328180, 123.907230, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Ettas Cucina + Bar", 10.335324, 123.933825, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Loca Kitchen + Cafe", 10.280389, 123.881836, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("MFBC Recess food cart", 10.311611, 123.918796, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Ramen Shack & Sushi House", 10.241801, 123.796639, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Seafood & Ribs Warehouse", 10.280489, 123.881936, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Topokkiman Talisay", 10.247382, 123.846579, GOLDENROD, 6));

        map.addMapMarker(new LabeledDot("Nomnom Desserts", 10.257656, 123.828301, Color.GREEN, 6));
        map.addMapMarker(new LabeledDot("Mille Crepe by Kari", 10.344999, 123.908584, Color.GREEN, 6));

        map.repaint();
    }

    public static class YouDot extends MapMarkerDot {
        private final String label;
        private final int customRadius;

        public YouDot(String label, double lat, double lon, int radius) {
            super(lat, lon);
            this.label = label + " (" + String.format("%.5f, %.5f", lat, lon) + ")";
            this.customRadius = radius;
        }

        @Override
        public void paint(Graphics g, Point position, int radiusIgnored) {
            g.setColor(Color.RED);
            g.fillOval(position.x - customRadius, position.y - customRadius, customRadius * 2, customRadius * 2);
            g.setColor(Color.BLACK);
            g.drawOval(position.x - customRadius, position.y - customRadius, customRadius * 2, customRadius * 2);
            g.setFont(LABEL_FONT);
            g.drawString(label, position.x + customRadius + 3, position.y - customRadius - 3);
        }
    }

    public static class LabeledDot extends MapMarkerDot {
        private final String label;
        private final Color color;
        private final int customRadius;

        public LabeledDot(String label, double lat, double lon, Color color, int radius) {
            super(lat, lon);
            this.label = label + " (" + String.format("%.5f, %.5f", lat, lon) + ")";
            this.color = color;
            this.customRadius = radius;
        }

        @Override
        public void paint(Graphics g, Point position, int radiusIgnored) {
            g.setColor(color);
            g.fillOval(position.x - customRadius, position.y - customRadius, customRadius * 2, customRadius * 2);
            g.setColor(Color.BLACK);
            g.drawOval(position.x - customRadius, position.y - customRadius, customRadius * 2, customRadius * 2);
            g.setFont(LABEL_FONT);
            g.drawString(label, position.x + customRadius + 3, position.y - customRadius - 3);
        }
    }

    public static class LegendPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public LegendPanel() {
            setOpaque(false);
            setPreferredSize(new Dimension(190, 160));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int y = 18, x = 14, sw = 18, sh = 18, gap = 26;
            g.setFont(LABEL_FONT);

            g.setColor(Color.RED);
            g.fillOval(x, y, sw, sh);
            g.setColor(Color.BLACK);
            g.drawString("CEBU CITY", x + sw + 10, y + sh - 2);

            y += gap;
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, sw, sh);
            g.setColor(Color.BLACK);
            g.drawString("Cafes", x + sw + 10, y + sh - 2);

            y += gap;
            Color GOLDENROD = new Color(218, 165, 32);
            g.setColor(GOLDENROD);
            g.fillOval(x, y, sw, sh);
            g.setColor(Color.BLACK);
            g.drawString("Restaurants", x + sw + 10, y + sh - 2);

            y += gap;
            g.setColor(Color.GREEN);
            g.fillOval(x, y, sw, sh);
            g.setColor(Color.BLACK);
            g.drawString("Desserts", x + sw + 10, y + sh - 2);

            y += gap;
            Color USC = new Color(80, 200, 120);
            g.setColor(USC);
            g.fillOval(x, y, sw, sh);
            g.setColor(Color.BLACK);
            g.drawString("USC Talamban Campus", x + sw + 10, y + sh - 2);
        }
    }
}