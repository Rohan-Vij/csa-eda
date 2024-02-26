package com.csa.app;

public class DisasterRecord {
   private int year;
   private String seq;
   private String glide;
   private String disasterGroup;
   private String disasterSubgroup;
   private String disasterType;
   private String disasterSubtype;
   private String disasterSubsubtype;
   private String eventName;
   private String country;
   private String iso;
   private String region;
   private String continent;
   private String location;
   private String origin;
   private String associatedDis;
   private String associatedDis2;
   private String ofdaResponse;
   private String appeal;
   private String declaration;
   private String aidContribution;
   private String disMagValue;
   private String disMagScale;
   private String latitude;
   private String longitude;
   private String localTime;
   private String riverBasin;
   private int startYear;
   private int startMonth;
   private int startDay;
   private int endYear;
   private int endMonth;
   private int endDay;
   private int totalDeaths;
   private int noInjured;
   private int noAffected;
   private int noHomeless;
   private int totalAffected;
   private int insuredDamages;
   private int totalDamages;
   private double cpi;
   private String admLevel;
   private String admin1Code;
   private String admin2Code;
   private String geoLocations;

   public DisasterRecord(int year, String seq, String glide, String disasterGroup, String disasterSubgroup,
         String disasterType, String disasterSubtype, String disasterSubsubtype, String eventName,
         String country, String iso, String region, String continent, String location, String origin,
         String associatedDis, String associatedDis2, String ofdaResponse, String appeal, String declaration,
         String aidContribution, String disMagValue, String disMagScale, String latitude, String longitude,
         String localTime, String riverBasin, int startYear, int startMonth, int startDay, int endYear,
         int endMonth, int endDay, int totalDeaths, int noInjured, int noAffected, int noHomeless,
         int totalAffected, int insuredDamages, int totalDamages, double cpi, String admLevel,
         String admin1Code, String admin2Code, String geoLocations) {
      this.year = year;
      this.seq = seq;
      this.glide = glide;
      this.disasterGroup = disasterGroup;
      this.disasterSubgroup = disasterSubgroup;
      this.disasterType = disasterType;
      this.disasterSubtype = disasterSubtype;
      this.disasterSubsubtype = disasterSubsubtype;
      this.eventName = eventName;
      this.country = country;
      this.iso = iso;
      this.region = region;
      this.continent = continent;
      this.location = location;
      this.origin = origin;
      this.associatedDis = associatedDis;
      this.associatedDis2 = associatedDis2;
      this.ofdaResponse = ofdaResponse;
      this.appeal = appeal;
      this.declaration = declaration;
      this.aidContribution = aidContribution;
      this.disMagValue = disMagValue;
      this.disMagScale = disMagScale;
      this.latitude = latitude;
      this.longitude = longitude;
      this.localTime = localTime;
      this.riverBasin = riverBasin;
      this.startYear = startYear;
      this.startMonth = startMonth;
      this.startDay = startDay;
      this.endYear = endYear;
      this.endMonth = endMonth;
      this.endDay = endDay;
      this.totalDeaths = totalDeaths;
      this.noInjured = noInjured;
      this.noAffected = noAffected;
      this.noHomeless = noHomeless;
      this.totalAffected = totalAffected;
      this.insuredDamages = insuredDamages;
      this.totalDamages = totalDamages;
      this.cpi = cpi;
      this.admLevel = admLevel;
      this.admin1Code = admin1Code;
      this.admin2Code = admin2Code;
      this.geoLocations = geoLocations;
   }
}
