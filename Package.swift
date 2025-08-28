// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AalzehlaCapacitorSecureStoragePlugin",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "SecureStoragePlugin",
            targets: ["SecureStoragePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "7.4.3")
    ],
    targets: [
        .target(
            name: "SecureStoragePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/SecureStoragePlugin"),
        .testTarget(
            name: "SecureStoragePluginTests",
            dependencies: ["SecureStoragePlugin"],
            path: "ios/Tests/SecureStoragePluginTests")
    ]
)