#include <iostream>
#include <thread>
#include <vector>
#include <random>
#include <atomic>
#include <cmath>
#include <cstdlib>

// Function to integrate (modify as needed)
double function(double x) {
    return sin(x); // Example: sin(x)
}

struct ThreadArgs {
    double x_lower;
    double x_upper;
    double max_y;
    long long num_points;
    int thread_id;
    int num_threads;
    std::atomic<long long>& points_under_curve;
};

void monte_carlo_thread(ThreadArgs args) {
    long long local_count = 0;
    long long points_per_thread = args.num_points / args.num_threads;
    
    if (args.thread_id == args.num_threads - 1) {
        points_per_thread = args.num_points - (args.num_threads - 1) * points_per_thread;
    }
    
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<double> x_dist(args.x_lower, args.x_upper);
    std::uniform_real_distribution<double> y_dist(0, args.max_y);
    
    for (long long i = 0; i < points_per_thread; ++i) {
        double x = x_dist(gen);
        double y = y_dist(gen);
        if (y <= function(x)) {
            local_count++;
        }
    }
    
    args.points_under_curve += local_count;
}

int main(int argc, char* argv[]) {
    if (argc != 5) {
        std::cerr << "Usage: " << argv[0] << " <x_lower> <x_upper> <max_y> <num_points>\n";
        return 1;
    }
    
    double x_lower = std::stod(argv[1]);
    double x_upper = std::stod(argv[2]);
    double max_y = std::stod(argv[3]);
    long long num_points = std::stoll(argv[4]);
    
    const int num_threads = 8;
    std::atomic<long long> points_under_curve(0);
    std::vector<std::thread> threads;
    
    for (int i = 0; i < num_threads; ++i) {
        ThreadArgs args{x_lower, x_upper, max_y, num_points, i, num_threads, points_under_curve};
        threads.emplace_back(monte_carlo_thread, args);
    }
    
    for (auto& thread : threads) {
        thread.join();
    }
    
    double rectangle_area = (x_upper - x_lower) * max_y;
    double estimated_area = rectangle_area * points_under_curve / num_points;
    
    std::cout << "Estimated Area: " << estimated_area << std::endl;
    return 0;
}