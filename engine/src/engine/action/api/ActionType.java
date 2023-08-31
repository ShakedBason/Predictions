package engine.action.api;

public enum ActionType {
    INCREASE{
        @Override
        public String toString() {
            return "INCREASE";
        }
    }, DECREASE{
        @Override
        public String toString() {
            return "DECREASE";
        }
    }, KILL{
        @Override
        public String toString() {
            return "KILL";
        }
    },SET{
        @Override
        public String toString(){return "SET";}
    },CALCULATION{
        @Override
        public String toString(){return "CALCULATION";}
    },CONDITION{
        @Override
        public String toString(){return "CONDITION";}
    }
}
