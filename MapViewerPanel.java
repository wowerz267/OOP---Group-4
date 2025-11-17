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
        map.addMapMarker(new LabeledDot("INFUSO COFFEE OUTLET", 10.309933, 123.894987, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("The Mezzanine Cafe", 10.305495, 123.899249, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("Ayala Central Bloc", 10.33056, 123.90722, Color.ORANGE, 6));
        map.addMapMarker(new LabeledDot("Cafe Francois", 10.320808, 123.899272, Color.ORANGE, 6));

        Color GOLDENROD = new Color(218, 165, 32);
        map.addMapMarker(new LabeledDot("Chikaan sa Cebu", 10.334068, 123.902936, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Brique Modern Kitchen", 10.334181, 123.902812, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Sutukil by the Bay", 10.280389, 123.8836, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Parola Seaview Restaurant", 10.235521, 123.944799, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Da-In Korean Restaurant", 10.334878, 123.902462, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Ku-Bu Restaurant + Bar", 10.328180, 123.907230, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Ettas Cucina + Bar", 10.299012, 123.876634, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Loca Kitchen + Cafe", 10.280389, 123.881836, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("MFBC Recess food cart", 10.311611, 123.918796, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Ramen Shack & Sushi House", 10.241801, 123.796639, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Seafood & Ribs Warehouse", 10.280489, 123.881936, GOLDENROD, 6));
        map.addMapMarker(new LabeledDot("Topokkiman Talisay", 10.247382, 123.846579, GOLDENROD, 6));

        map.addMapMarker(new LabeledDot("Nomnom Desserts", 10.257656, 123.828301, Color.GREEN, 6));
        map.addMapMarker(new LabeledDot("Mille Crepe by Kari", 10.345126, 123.916735, Color.GREEN, 6));

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
