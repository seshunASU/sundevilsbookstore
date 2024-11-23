public class SalesHistoryView extends View {

	private SalesHistoryPage createSalesHistoryPage;

    public SalesHistoryView() {
        super();
        
        createSalesHistoryPage = new SalesHistoryPage();

        setPage(createSalesHistoryPage);
    }

}
