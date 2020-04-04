package sistema.basics;


public class Proyecto {

@Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
        String landingDate = formatDate.format(this.flightdate.getTime());
        
            str = str.append(ID + ": " + this.id + NL);
            str = str.append(FLIGHTTIME + ": " + this.flightDuration + NL );
            str = str.append(LANDDATE +": " + landingDate + NL);
            str = str.append(LANDHOUR + ": " + this.landHour + NL );
        
        return str.toString();
    }
	
}