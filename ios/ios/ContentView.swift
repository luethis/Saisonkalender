import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        TabView {
            Products()
                .tabItem() {
                    Image(systemName: "lasso")
                    Text("Übersicht")
                }
            Notification()
                .tabItem() {
                    Image(systemName: "phone.fill")
                    Text("Benachrichtigung")
                }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
