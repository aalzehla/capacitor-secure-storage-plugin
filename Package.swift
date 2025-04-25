// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AalzehlaCapacitorSecureStoragePlugin",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "AalzehlaCapacitorSecureStoragePlugin",
            targets: ["CapacitorSecureStoragePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "CapacitorSecureStoragePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm"),
                .product(name: "SimpleKeychain", package: "capacitor-swift-pm")
            ],
            path: "ios/Plugin/CapacitorSecureStoragePlugin"),
        .testTarget(
            name: "SecureStoragePluginTests",
            dependencies: ["CapacitorSecureStoragePlugin"],
            path: "ios/PluginTests/CapacitorSecureStoragePluginTests")
    ]
)
