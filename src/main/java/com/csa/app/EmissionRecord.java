package com.csa.app;
import java.util.Date;

public class EmissionRecord {
   private final long sourceId;
   private final String iso3Country;
   private final String originalInventorySector;
   private final Date startTime;
   private final Date endTime;
   private final String temporalGranularity;
   private final String gas;
   private final double emissionsQuantity;
   private final Double emissionsFactor; // nullable
   private final String emissionsFactorUnits;
   private final Double capacity; // nullable
   private final String capacityUnits;
   private final double capacityFactor;
   private final double activity;
   private final String activityUnits;
   private final Date createdDate;
   private final Date modifiedDate;
   private final String sourceName;
   private final String sourceType;
   private final double lat;
   private final double lon;
   private final double other1;
   private final double other2;
   private final double other3;
   private final Double other4; // nullable
   private final String other1Def;
   private final String other2Def;
   private final String other3Def;
   private final String other4Def;
   private final String geometryRef;

   public EmissionRecord(long sourceId, String iso3Country, String originalInventorySector, Date startTime,
         Date endTime, String temporalGranularity, String gas, double emissionsQuantity, Double emissionsFactor,
         String emissionsFactorUnits, Double capacity, String capacityUnits, double capacityFactor, double activity,
         String activityUnits, Date createdDate, Date modifiedDate, String sourceName, String sourceType, double lat,
         double lon, double other1, double other2, double other3, Double other4, String other1Def, String other2Def,
         String other3Def, String other4Def, String geometryRef) {
      this.sourceId = sourceId;
      this.iso3Country = iso3Country;
      this.originalInventorySector = originalInventorySector;
      this.startTime = startTime;
      this.endTime = endTime;
      this.temporalGranularity = temporalGranularity;
      this.gas = gas;
      this.emissionsQuantity = emissionsQuantity;
      this.emissionsFactor = emissionsFactor;
      this.emissionsFactorUnits = emissionsFactorUnits;
      this.capacity = capacity;
      this.capacityUnits = capacityUnits;
      this.capacityFactor = capacityFactor;
      this.activity = activity;
      this.activityUnits = activityUnits;
      this.createdDate = createdDate;
      this.modifiedDate = modifiedDate;
      this.sourceName = sourceName;
      this.sourceType = sourceType;
      this.lat = lat;
      this.lon = lon;
      this.other1 = other1;
      this.other2 = other2;
      this.other3 = other3;
      this.other4 = other4;
      this.other1Def = other1Def;
      this.other2Def = other2Def;
      this.other3Def = other3Def;
      this.other4Def = other4Def;
      this.geometryRef = geometryRef;
   }

   public long getSourceId() {
      return sourceId;
   }

   public String getIso3Country() {
      return iso3Country;
   }

   public String getOriginalInventorySector() {
      return originalInventorySector;
   }

   public Date getStartTime() {
      return startTime;
   }

   public Date getEndTime() {
      return endTime;
   }

   public String getTemporalGranularity() {
      return temporalGranularity;
   }

   public String getGas() {
      return gas;
   }

   public double getEmissionsQuantity() {
      return emissionsQuantity;
   }

   public Double getEmissionsFactor() {
      return emissionsFactor;
   }

   public String getSourceName() {
      return sourceName;
   }

   public double getLat() {
      return lat;
   }

   public double getLon() {
      return lon;
   }

   public String toString() {
      return "EmissionRecord{" +
            "sourceId=" + sourceId +
            ", iso3Country='" + iso3Country + '\'' +
            ", originalInventorySector='" + originalInventorySector + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", temporalGranularity='" + temporalGranularity + '\'' +
            ", gas='" + gas + '\'' +
            ", emissionsQuantity=" + emissionsQuantity +
            ", emissionsFactor=" + emissionsFactor +
            ", sourceName='" + sourceName + '\'' +
            ", lat=" + lat +
            ", lon=" + lon +
            '}';
   }
}
