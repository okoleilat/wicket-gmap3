/*
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.gmap3.api;

import java.util.StringTokenizer;

import wicket.contrib.gmap3.js.Constructor;

/**
 * Represents an Google Maps API's <a href=
 * "http://www.google.com/apis/maps/documentation/reference.html#GLatLngBounds"
 * >GLatLngBounds</a>.
 */
public class GLatLngBounds implements GValue {
    /**
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private final LatLng _sw;
    private final LatLng _ne;

    /**
     * Construct.
     * 
     * @param sw
     * @param ne
     */
    public GLatLngBounds( LatLng sw, LatLng ne ) {
        _sw = sw;
        _ne = ne;
    }

    public LatLng getSW() {
        return _sw;
    }

    public LatLng getNE() {
        return _ne;
    }

    @Override
    public String toString() {
        return getJSconstructor();
    }

    /**
     * @see wicket.contrib.gmap.api.GValue#getJSconstructor()
     */
    public String getJSconstructor() {
        return new Constructor( "google.maps.LatLngBounds" ).add( _sw.getJSconstructor() ).add( _ne.getJSconstructor() ).toJS();
    }

    @Override
    public int hashCode() {
        return _sw.hashCode() ^ _ne.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj instanceof GLatLngBounds ) {
            GLatLngBounds t = (GLatLngBounds) obj;
            return t._sw.equals( _sw ) && t._ne.equals( _ne );
        }
        return false;
    }

    /**
     * ((37.34068368469045, -122.48519897460936), (37.72184917678752,
     * -121.79855346679686))
     */
    public static GLatLngBounds parse( String value ) {
        StringTokenizer tokenizer;
        try {
            tokenizer = new StringTokenizer( value, "(, )" );
        } catch ( NullPointerException e ) {
            return null;
        }
        if ( tokenizer.countTokens() != 4 ) {
            return null;
        }

        LatLng sw = new LatLng( Float.valueOf( tokenizer.nextToken() ), Float.valueOf( tokenizer.nextToken() ) );
        LatLng ne = new LatLng( Float.valueOf( tokenizer.nextToken() ), Float.valueOf( tokenizer.nextToken() ) );
        return new GLatLngBounds( sw, ne );
    }
}
