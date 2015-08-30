/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-08-03 17:34:38 UTC)
 * on 2015-08-30 at 11:19:41 UTC 
 * Modify at your own risk.
 */

package com.bujok.locationapp.backend.locationHistoryApi.model;

/**
 * Model definition for LocationHistory.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the locationHistoryApi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class LocationHistory extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String deviceID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private GeoPt geoPt;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private GeoPt latLng;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long locationHistoryID;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDeviceID() {
    return deviceID;
  }

  /**
   * @param deviceID deviceID or {@code null} for none
   */
  public LocationHistory setDeviceID(java.lang.String deviceID) {
    this.deviceID = deviceID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public GeoPt getGeoPt() {
    return geoPt;
  }

  /**
   * @param geoPt geoPt or {@code null} for none
   */
  public LocationHistory setGeoPt(GeoPt geoPt) {
    this.geoPt = geoPt;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public GeoPt getLatLng() {
    return latLng;
  }

  /**
   * @param latLng latLng or {@code null} for none
   */
  public LocationHistory setLatLng(GeoPt latLng) {
    this.latLng = latLng;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getLocationHistoryID() {
    return locationHistoryID;
  }

  /**
   * @param locationHistoryID locationHistoryID or {@code null} for none
   */
  public LocationHistory setLocationHistoryID(java.lang.Long locationHistoryID) {
    this.locationHistoryID = locationHistoryID;
    return this;
  }

  @Override
  public LocationHistory set(String fieldName, Object value) {
    return (LocationHistory) super.set(fieldName, value);
  }

  @Override
  public LocationHistory clone() {
    return (LocationHistory) super.clone();
  }

}
