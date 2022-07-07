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
                        Task {
                            do {
                                try await KoinHelper().loadProducts().collectCommon(callback: { bla in
                                    isLoading = bla!.loading
                                    if(bla!.products != nil){
                                        self.demoData = bla!.products! as! [Product]
                                    }
                            })
                            }
                        }
                }
                ForEach(demoData, id: \.name) { data in
                    NavigationLink(destination: Text(data.description_["de"]!)){
                        Text(data.name["de"]!)
                    }
       
                    }
                if self.isLoading {
                    ProgressView("loading mock data")
                }
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
