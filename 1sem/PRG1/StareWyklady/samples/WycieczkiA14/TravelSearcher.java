public class TravelSearcher {

  private Travel[] travel;

  public TravelSearcher(Travel[] t) {
    travel = new Travel[t.length];
    for (int i=0; i < t.length; i++) travel[i] = t[i];   
    sortByDest();
  }

    
  public Travel search(String dest) {
    int low = 0;
    int high = travel.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      int compRes = dest.compareToIgnoreCase(travel[mid].getDest());
      if (compRes < 0) high = mid - 1;
      else if (compRes > 0) low = mid + 1;
           else return travel[mid];
    }
    return null;
  }

 public Travel[] getTravels() { return travel; }

 private void sortByDest() {
   for (int to=travel.length-1; to>0; to--) {
      int i = 0;   
      for (int k=1; k <= to; k++) 
 	if (travel[i].getDest().compareTo(travel[k].getDest()) < 0) i = k; 
      Travel temp = travel[to];
      travel[to] = travel[i];
      travel[i] = temp;
    }
  }     


 
}

