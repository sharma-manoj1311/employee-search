package com.exl.services.employeesearch.forms;





import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.exl.services.employeesearch.utils.Validate;
import com.library.common.StringHelper;

public class DatatablesCriterias implements Serializable {

    private static final long serialVersionUID = 8661357461501153387L;

    private static Pattern pattern = Pattern.compile("columns\\[([0-9]*)?\\]");
    private final String search;
    private final Integer start;
    private final Integer length;
    private final List<ColumnDef> columnDefs;
    private final List<ColumnDef> sortedColumnDefs;
    private final Integer draw;
    private final String searchText;
    private final String startDate;
    private final String endDate;

    

    public DatatablesCriterias(String search, Integer start, Integer length, List<ColumnDef> columnDefs,
			List<ColumnDef> sortedColumnDefs, Integer draw, String searchText,
			String startDate, String endDate) {
		super();
		this.search = search;
		this.start = start;
		this.length = length;
		this.columnDefs = columnDefs;
		this.sortedColumnDefs = sortedColumnDefs;
		this.draw = draw;
		this.searchText = searchText;
		this.startDate = startDate;
		this.endDate = endDate;
	}

    public static DatatablesCriterias getFromRequest(HttpServletRequest request) {

        Validate.notNull(request, "The HTTP request cannot be null");

        int columnNumber = getColumnNumber(request);

        String paramSearch = request.getParameter(DTConstants.DT_S_SEARCH);
        String paramDraw = request.getParameter(DTConstants.DT_I_DRAW);
        String paramStart = request.getParameter(DTConstants.DT_I_START);
        String paramLength = request.getParameter(DTConstants.DT_I_LENGTH);
        String searchText = request.getParameter(DTConstants.DT_PM_SEARCHTEXT);
        String startDate = request.getParameter(DTConstants.DT_PM_FROM_DATE);
        String endDate = request.getParameter(DTConstants.DT_PM_TO_DATE);

        System.out.println(paramSearch+"--------"    +paramDraw+"--------"+paramStart+"--------"+columnNumber);
        Integer draw = StringHelper.isNotEmpty(paramDraw) ? Integer.parseInt(paramDraw) : -1;
        Integer start = StringHelper.isNotEmpty(paramStart) ? Integer.parseInt(paramStart) : -1;
        Integer length = StringHelper.isNotEmpty(paramLength) ? Integer.parseInt(paramLength) : -1;

        // Column definitions
        List<ColumnDef> columnDefs = new ArrayList<ColumnDef>();

        for (int i = 0; i < columnNumber; i++) {

            ColumnDef columnDef = new ColumnDef();

            columnDef.setName(request.getParameter("columns[" + i + "][data]"));
            columnDef.setSearchable(Boolean.parseBoolean(request.getParameter("columns[" + i + "][searchable]")));
            columnDef.setSortable(Boolean.parseBoolean(request.getParameter("columns[" + i + "][orderable]")));
            //columnDef.setRegex(request.getParameter("columns[" + i + "][search][regex]"));

            String searchTerm = request.getParameter("columns[" + i + "][search][value]");

            if (StringHelper.isNotEmpty(searchTerm)) {
                columnDef.setFiltered(true);
                String[] splittedSearch = searchTerm.split("~");
                if ("~".equals(searchTerm)) {
                    columnDef.setSearch("");
                } else if (searchTerm.startsWith("~")) {
                    columnDef.setSearchTo(splittedSearch[1]);
                } else if (searchTerm.endsWith("~")) {
                    columnDef.setSearchFrom(splittedSearch[0]);
                } else if (searchTerm.contains("~")) {
                    columnDef.setSearchFrom(splittedSearch[0]);
                    columnDef.setSearchTo(splittedSearch[1]);
                } else {
                    columnDef.setSearch(searchTerm);
                }
            }

            for (int j = 0; j < columnNumber; j++) {
                String ordered = request.getParameter("order[" + j + "][column]");
                if (ordered != null && ordered.equals(String.valueOf(i))) {
                    columnDef.setSorted(true);
                    break;
                }
            }

            columnDefs.add(columnDef);
        }

        // Sorted column definitions
        List<ColumnDef> sortedColumnDefs = new LinkedList<ColumnDef>();

        for (int i = 0; i < columnNumber; i++) {
            String paramSortedCol = request.getParameter("order[" + i + "][column]");

            // The column is being sorted
            if (StringHelper.isNotEmpty(paramSortedCol)) {
                Integer sortedCol = Integer.parseInt(paramSortedCol);
                ColumnDef sortedColumnDef = columnDefs.get(sortedCol);
                String sortedColDirection = request.getParameter("order[" + i + "][dir]");
                if (StringHelper.isNotEmpty(sortedColDirection)) {
                    sortedColumnDef.setSortDirection(ColumnDef.SortDirection.valueOf(sortedColDirection.toUpperCase()));
                }

                sortedColumnDefs.add(sortedColumnDef);
            }
        }

        return new DatatablesCriterias(paramSearch, start, length, columnDefs, sortedColumnDefs, draw, searchText,
    			 startDate,  endDate);
    }

    private static int getColumnNumber(HttpServletRequest request) {

        int columnNumber = 0;
        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ) {
            String param = e.nextElement();
            Matcher matcher = pattern.matcher(param);
            while (matcher.find()) {
                Integer col = Integer.parseInt(matcher.group(1));
                if (col > columnNumber) {
                    columnNumber = col;
                }
            }
        }

        if (columnNumber != 0) {
            columnNumber++;
        }
        return columnNumber;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getLength() {
        return length;
    }

    public String getSearch() {
        return search;
    }

    public Integer getDraw() {
        return draw;
    }

    public String getSearchText() {
		return searchText;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public List<ColumnDef> getColumnDefs() {
        return columnDefs;
    }

    public List<ColumnDef> getSortingColumnDefs() {
        return sortedColumnDefs;
    }

    public List<ColumnDef> getSortedColumnDefs() {
        return sortedColumnDefs;
    }
    public Boolean hasOneFilterableColumn() {
        return hasOneSearchableColumn();
    }

    public Boolean hasOneSearchableColumn() {
        for (ColumnDef columnDef : this.columnDefs) {
            if (columnDef.isSearchable()) {
                return true;
            }
        }
        return false;
    }
    public Boolean hasOneFilteredColumn() {
        for (ColumnDef columnDef : this.columnDefs) {
            if (StringHelper.isNotEmpty(columnDef.getSearch()) || StringHelper.isNotEmpty(columnDef.getSearchFrom())
                    || StringHelper.isNotEmpty(columnDef.getSearchTo())) {
                return true;
            }
        }
        return false;
    }
    public Boolean hasOneSortedColumn() {
        return !sortedColumnDefs.isEmpty();
    }

	@Override
	public String toString() {
		return "DatatablesCriterias [search=" + search + ", start=" + start + ", length=" + length + ", columnDefs="
				+ columnDefs + ", sortedColumnDefs=" + sortedColumnDefs + ", draw=" + draw + ", searchText="
				+ searchText + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

   
}
