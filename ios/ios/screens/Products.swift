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
                                    if(bla!.data != nil){
                                        self.demoData = bla!.data! as! [Product]
                                    }
                            })
                            }
                        }
                }
                ForEach(demoData, id: \.name) { data in
                    NavigationLink(destination: Text(data.description)){
                        Text(data.name)
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
