package engine.definition.property.api;

public enum PropertyType {
    DECIMAL{
        @Override
        public Integer convertFromStringToPropertyType(String value) {
            return Integer.valueOf(value);
        }

        @Override
        public boolean isNumericPropertyType() {
            return true;
        }

        public boolean isStringValueMatchType(String value){
            return value.matches(("-?\\d+") );
        }

    }, BOOLEAN {

        public boolean isStringValueMatchType(String value){return value.equalsIgnoreCase("true")||value.equalsIgnoreCase("false");}

        @Override
        public boolean isNumericPropertyType() {
            return false;
        }

        @Override
        public Boolean convertFromStringToPropertyType(String value) {
            return Boolean.valueOf(value);
        }
    }, FLOAT {

        public boolean isStringValueMatchType(String value){return value.matches(("-?\\d+(\\.\\d+)?"));}

        @Override
        public boolean isNumericPropertyType() {
            return true;
        }

        @Override
        public Float convertFromStringToPropertyType(String value) {
            return Float.valueOf(value);
        }
    }, STRING {


        @Override
        public String convertFromStringToPropertyType(String value) {
            return value;
        }

        @Override
        public boolean isNumericPropertyType() {
            return false;
        }

        public boolean isStringValueMatchType(String value){return true;}
    };

    public abstract boolean isStringValueMatchType(String value);

    public abstract<T> T convertFromStringToPropertyType(String value);
    public abstract boolean isNumericPropertyType();
}
