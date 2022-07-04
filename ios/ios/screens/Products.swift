import SwiftUI
import shared
import Combine

struct Products: View {
    
    @State var demoData = [] as [Product]
    @State var isLoading = false
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical) {
                LazyVStack {
                    Button ("load data"){
                        Interactor(repository: FirestoreRepository()).getProducts().collectCommon() { queryState in
                            if(queryState != nil){
                                self.demoData = queryState!.products ?? []
                            }
                            self.isLoading = queryState?.loading ?? false
                        }
                    }
                    
                    ForEach(demoData, id: \.name) { data in
                        NavigationLink(destination: Text(data.description_)){
                            Text(data.name)
                        }
                    }
                }
                if self.isLoading {
                    ProgressView("loading mock data")
                }
            }
        }
    }
}

struct Products_Previews: PreviewProvider {
    static var previews: some View {
        Products()
    }
}
