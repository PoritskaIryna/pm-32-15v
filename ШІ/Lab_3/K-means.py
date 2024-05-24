import numpy as np
import matplotlib.pyplot as plt

def k_means(X, K, max_iters=100):
    N,D = X.shape
    centroids = X[np.random.choice(N, K, replace=False)]  # Initializes K centroids randomly by selecting K unique data points from X.
    

    for _ in range(max_iters):
        # Assign each data point to the nearest centroid
        distances = np.sqrt(((X - centroids[:, np.newaxis])**2).sum(axis=2))          #Calculates the Euclidean distance between each data point and each centroid.
        labels = np.argmin(distances, axis=0)

        # Update centroids based on the mean of the data points assigned to each centroid
        for k in range(K):
            centroids[k] = X[labels == k].mean(axis=0)
         # Calculate average distance within each cluster
        avg_distances = [np.mean(distances[k, labels == k]) for k in range(K)]
      
  

    return centroids, labels,avg_distances


def show_plot(X,K):
    #centroids, labels, avg_distances = k_means(X, K)
    result = k_means(X, K)
    centroids = result[0]
    labels = result[1]
    avg_distances = result[2]
    # Plot the data points with different colors for each cluster
    plt.scatter(X[:, 0], X[:, 1], c=labels, cmap='viridis')
    plt.scatter(centroids[:, 0], centroids[:, 1], marker='x', c='red', s=100)  # Plot centroids
    plt.title('K-means Clustering')
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.colorbar(label='Cluster')
    #plt.show()

     # Plot average distances
    plt.figure()
    plt.bar(range(K), avg_distances)
    plt.title('Average Distance in Each Cluster')
    plt.xlabel('Cluster')
    plt.ylabel('Average Distance')
    plt.show()


# Example usage:
# Generate some sample data
np.random.seed(0)
N = 1000
D = 2
K = 3
X = np.random.randn(N, D)

# Run K-means clustering algorithm
#centroids, labels = k_means(X, K)
show_plot(X,K)
show_plot(X,5)

