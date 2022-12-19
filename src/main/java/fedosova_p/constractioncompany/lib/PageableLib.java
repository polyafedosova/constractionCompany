package fedosova_p.constractioncompany.lib;

import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PageableLib {
    public static List<Integer> getCountPage(Page page) {
        int totalPages = page.getTotalPages();
        int pageNumber = page.getNumber() + 1;
        List<Integer> body = new LinkedList<>();

        if (totalPages < 8) {
            body.addAll(IntStream.rangeClosed(1, totalPages)
                    .boxed().toList());
        }
        else {
            List<Integer> head = (pageNumber > 4) ? Arrays.asList(1, -1) : Arrays.asList(1, 2, 3);
            List<Integer> tail = (pageNumber < totalPages - 3) ?
                    Arrays.asList(-1, totalPages) : Arrays.asList(totalPages - 2, totalPages - 1, totalPages);
            List<Integer> bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ?
                    Arrays.asList(pageNumber - 2, pageNumber - 1) : List.of();
            List<Integer> bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ?
                    Arrays.asList(pageNumber + 1, pageNumber + 2) : List.of();

            body.addAll(head);
            body.addAll(bodyBefore);
            if(pageNumber > 3 && pageNumber < totalPages - 2) body.add(pageNumber);
            body.addAll(bodyAfter);
            body.addAll(tail);
        }
        return body;
    }
}
