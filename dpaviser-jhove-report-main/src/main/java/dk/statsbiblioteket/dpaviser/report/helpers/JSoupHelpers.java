package dk.statsbiblioteket.dpaviser.report.helpers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.jsoup.Jsoup.parse;

public class JSoupHelpers {
    /**
     * Converts the first table in a HTML snippet to a list of list of strings.
     * <p>
     * <table> <tr><td> <table border="1"><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table> </td><td>
     * <code>-> [["1", "2"], ["3", "4"]]</code>. </td></tr></table> See <a href="http://stackoverflow.com/a/24773413/53897">http://stackoverflow.com/a/24773413/53897</a>
     * for JSoup implementation details.
     *
     * @param result string representation of the HTML table.
     * @return list representation of the table.
     */
    public static List<List<String>> getFirstTableFromHTML(String result) {
        //
        Document doc = parse(result);
        Element table = doc.select("table").get(0);

        // convert each table row to a list of the text of each td for that row. Note: select() returns an ArrayList.
        List<List<String>> rowList = table.select("tr").stream().map(
                tr -> tr.select("td").stream().map(element -> element.text()).collect(toList())
        ).collect(toList());
        return rowList;
    }


}
