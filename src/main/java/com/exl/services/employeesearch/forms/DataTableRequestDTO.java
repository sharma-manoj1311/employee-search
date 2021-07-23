package com.exl.services.employeesearch.forms;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import com.library.common.StringHelper;


public class DataTableRequestDTO {
	private static final long serialVersionUID = 8661357461501153387L;

	private final String search;
	private final Integer start;
	private final Integer length;
	private final List<ColumnDef> columnDefs;
	private final List<ColumnDef> sortedColumnDefs;
	private final Integer draw;
	private final String searchText;
    private final String startDate;
    private final String endDate;

	public DataTableRequestDTO(String search, Integer start, Integer length, List<ColumnDef> columnDefs,
			List<ColumnDef> sortedColumnDefs, Integer draw, String searchText, String startDate, String endDate) {
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

	public static DataTableRequestDTO getRequestParamData(DatatablesRequestCriterias dataTableRequestDTO) {

		String paramSearch = dataTableRequestDTO.getSearch().getValue();
		Integer paramDraw = dataTableRequestDTO.getDraw();
		Integer paramStart = dataTableRequestDTO.getStart();
		Integer paramLength = dataTableRequestDTO.getLength();
		String searchText = dataTableRequestDTO.getSearchText();
		String startDate = dataTableRequestDTO.getStartDate();
		String endDate = dataTableRequestDTO.getEndDate();

		System.out.println(paramSearch + "--------" + paramDraw + "--------" + paramStart + "--------");
		Integer draw = StringHelper.isNotEmpty(paramDraw.toString()) ? paramDraw : -1;
		Integer start = StringHelper.isNotEmpty(paramStart.toString()) ? paramStart : -1;
		Integer length = StringHelper.isNotEmpty(paramLength.toString()) ? paramLength : -1;

		// Column definitions
		List<ColumnDef> columnDefs = new ArrayList<ColumnDef>();

		for (int i = 0; i < dataTableRequestDTO.getColumns().size(); i++) {

			ColumnDef columnDef = new ColumnDef();

			columnDef.setName(dataTableRequestDTO.getColumns().get(i).getData());
			columnDef.setSearchable(dataTableRequestDTO.getColumns().get(i).getSearchable());
			columnDef.setSortable(dataTableRequestDTO.getColumns().get(i).getOrderable());
			columnDef.setRegex(dataTableRequestDTO.getColumns().get(i).getSearch().getRegex());

			String searchTerm = dataTableRequestDTO.getColumns().get(i).getSearch().getValue();

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

			for (int j = 0; j < dataTableRequestDTO.getOrder().size(); j++) {
				Integer ordered = dataTableRequestDTO.getOrder().get(j).getColumn();
				if (ordered != null && ordered == i) {
					columnDef.setSorted(true);
					break;
				}
			}

			columnDefs.add(columnDef);
		}

		// Sorted column definitions
		List<ColumnDef> sortedColumnDefs = new LinkedList<ColumnDef>();

		for (int i = 0; i < dataTableRequestDTO.getOrder().size(); i++) {
			Integer paramSortedCol = dataTableRequestDTO.getOrder().get(i).getColumn();

			// The column is being sorted
			if (paramSortedCol != null) {
				ColumnDef sortedColumnDef = columnDefs.get(paramSortedCol);
				String sortedColDirection = dataTableRequestDTO.getOrder().get(i).getDir();
				if (StringHelper.isNotEmpty(sortedColDirection)) {
					sortedColumnDef.setSortDirection(ColumnDef.SortDirection.valueOf(sortedColDirection.toUpperCase()));
				}

				sortedColumnDefs.add(sortedColumnDef);
			}
		}

		return new DataTableRequestDTO(paramSearch, start, length, columnDefs, sortedColumnDefs, draw, searchText,
				startDate, endDate);
	}

	public String getSearch() {
		return search;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getLength() {
		return length;
	}

	public List<ColumnDef> getColumnDefs() {
		return columnDefs;
	}

	public List<ColumnDef> getSortedColumnDefs() {
		return sortedColumnDefs;
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
		return "DataTableRequestDTO [search=" + search + ", start=" + start + ", length=" + length + ", columnDefs="
				+ columnDefs + ", sortedColumnDefs=" + sortedColumnDefs + ", draw=" + draw + ", searchText="
				+ searchText + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	

	
	

}
