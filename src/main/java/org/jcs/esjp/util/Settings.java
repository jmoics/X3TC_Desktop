package org.jcs.esjp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Settings
{
    /**
     * Logger for this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class);

    public enum FactorySettings
    {
        ASTILLERO("-----Astillero"),
        MUELLE("-----Muelle"),
        ESTACION_COMERCIAL("-----Estación comercial"),
        FACTORIA("-----Factoría"),
        MENA("-----Mena"),
        SILICIO("-----Obleas de Silicio"),
        NVIDIUM("-----Nividium"),
        HIELO("-----Hielo"),
        VACIO("-----");

        private final String key;

        private FactorySettings(final String _key)
        {
            this.key = _key;
        }

        public String getKey()
        {
            return this.key;
        }
    }

    public enum RaceSettings
    {
        ARGON("Argon", "images/argon.png"),
        TERRAN("Terran", "images/terran.png"),
        BORON("Boron", "images/boron.png"),
        TELADI("Teladi", "images/teladi.png"),
        PARANID("Paranid", "images/paranid.png"),
        SPLIT("Split", "images/split.png"),
        PIRATAS("Piratas", "images/pirata.png"),
        XENON("Xenon", "images/xenon.png"),
        KHAAK("Kha'ak", "images/khaak.png"),
        YAKI("Yaki", "images/yaki.png"),
        UNKNOWN("Desconocida", "images/desconocido.png"),
        GONER("Goner", "images/goner.png");

        private final String key;
        private final String color;

        private RaceSettings(final String _key,
                             final String _color)
        {
            this.key = _key;
            this.color = _color;
        }

        public String getKey()
        {
            return this.key;
        }

        public String getColor()
        {
            return this.color;
        }
    }

    public static Map<String, String> RACE2COLOR = new HashMap<String, String>();
    static {
        Settings.RACE2COLOR.put("Argon", "images/argon.png");
        Settings.RACE2COLOR.put("Terran", "images/terran.png");
        Settings.RACE2COLOR.put("Boron", "images/boron.png");
        Settings.RACE2COLOR.put("Teladi", "images/teladi.png");
        Settings.RACE2COLOR.put("Paranid", "images/paranid.png");
        Settings.RACE2COLOR.put("Split", "images/split.png");
        Settings.RACE2COLOR.put("Piratas", "images/pirata.png");
        Settings.RACE2COLOR.put("Xenon", "images/xenon.png");
        Settings.RACE2COLOR.put("Kha'ak", "images/khaak.png");
        Settings.RACE2COLOR.put("Yaki", "images/yaki.png");
        Settings.RACE2COLOR.put("Goner", "images/goner.png");
        Settings.RACE2COLOR.put("Desconocida", "images/desconocido.png");
        Settings.RACE2COLOR.put("Player", "images/terran.png");
    }

    public enum GateSettings
    {
        NORTH("NO"),
        SOUTH("SO"),
        EAST("EA"),
        WEST("WE");

        private final String key;

        private GateSettings(final String _key)
        {
            this.key = _key;
        }

        public String getKey()
        {
            return this.key;
        }
    }

    public static Map<Integer, String> CODE2TYPE = new TreeMap<Integer, String>();
    static {
        Settings.CODE2TYPE.put(1, "");
        Settings.CODE2TYPE.put(2, "");
        Settings.CODE2TYPE.put(3, "");
        Settings.CODE2TYPE.put(4, "");
        Settings.CODE2TYPE.put(5, "EquipmentDock");
        Settings.CODE2TYPE.put(6, "Factory");
        Settings.CODE2TYPE.put(7, "Ship");
        Settings.CODE2TYPE.put(8, "Weapon");
        Settings.CODE2TYPE.put(9, "Shield");
        Settings.CODE2TYPE.put(10, "Misil");
        Settings.CODE2TYPE.put(11, "EnergyCell");
        Settings.CODE2TYPE.put(12, "");
        Settings.CODE2TYPE.put(13, "Consumable1");
        Settings.CODE2TYPE.put(14, "Consumable2");
        Settings.CODE2TYPE.put(15, "Ore");
        Settings.CODE2TYPE.put(16, "Improvement");
        Settings.CODE2TYPE.put(17, "");
    }

    public enum SearchSettings
    {
        SECTOR("Sector"),
        DOCK("Muelle"),
        FACTORY("Fabrica"),
        MINQUANTITY("MinQuantity"),
        NOMINORPERCENT("NoMenorPercentage"),
        MINPRICE("MinPrice"),
        MAXPRICE("MaxPrice"),
        FREESHIP("FreeShip"),;

        private final String key;

        private SearchSettings(final String _key)
        {
            this.key = _key;
        }

        public String getKey()
        {
            return this.key;
        }
    }

    public static Properties getProperties()
    {
        final Properties prop = new Properties();
        try {
            prop.load(Settings.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (final IOException e) {
            Settings.LOG.error("Error reading properties file", e);
        }
        return prop;
    }
}
