package dto;

public  class PropertyDetailsDto {
 private final String PropertyName;
 private final String type;
 private final Double from;
 private final Double to;
 private final boolean isRandom;

 public PropertyDetailsDto(String propertyName, String type, Double from, Double to, boolean isRandom) {
  PropertyName = propertyName;
  this.type = type;
  this.from = from;
  this.to = to;
  this.isRandom = isRandom;
 }

 public boolean isRandom() {
  return isRandom;
 }

 public Double getFrom() {
  return from;
 }// return double

 public Double getTo() {
  return to;
 }

 public String getPropertyName() {
  return PropertyName;
 }

 public String getType() {
  return type;
 }
}
