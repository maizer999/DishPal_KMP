import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
               .ignoresSafeArea(.keyboard)
               .onOpenURL { url in // handling the deep link
                     MainViewControllerKt.onEvent(eventType: ComposeApp.EventType.deepLink, params: url.absoluteString)
               }
    }
}



