//package Scraping;
//
//import org.geonames.*;
//
//import java.util.List;
//
///**
// * Created by edoyle on 1/23/18.
// */
//public class Scraper {
//    public static void main(String [] args){
//        try {
//            WebService.setUserName("edoyle1221");
//            ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//            searchCriteria.setQ(args[0]);
//            ToponymSearchResult searchResult = WebService.search(searchCriteria);
//            List<Toponym> results = searchResult.getToponyms();
//            searchCriteria = new ToponymSearchCriteria();
//            searchCriteria.setBoundingBox(results.get(0).getBoundingBox());
//            searchResult = WebService.search(searchCriteria);
//            System.out.println("count: " + searchResult.getTotalResultsCount());
//            int count = searchResult.getTotalResultsCount();
//            int current = 1;
//            while (current < count) {
//                searchCriteria.setStartRow(current);
//                searchResult = WebService.search(searchCriteria);
//                for (Toponym toponym : searchResult.getToponyms()) {
//                    System.out.println(toponym.getName() + " " + toponym.getCountryName());
//                }
//                current += 100;
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error retrieving query!");
//            System.out.println(e.toString());
//        }
//    }
//}
//
