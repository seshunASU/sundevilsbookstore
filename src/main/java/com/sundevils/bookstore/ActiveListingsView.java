public class ActiveListingsView extends View {
	
	private ActiveListingsPage createActiveListingsPage;

    public ActiveListingsView() {
        super();
        
        createActiveListingsPage = new ActiveListingsPage();

        setPage(createActiveListingsPage);
    }


}
