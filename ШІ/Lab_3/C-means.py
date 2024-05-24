import numpy as np
import matplotlib.pyplot as plt

class CMeans:
    def __init__(self, X, num_clusters, fuzziness=2, max_iter=100, tol=1e-4):
        self.X = X
        self.num_clusters = num_clusters
        self.N, self.D = X.shape
        self.membership = np.random.dirichlet(np.ones(num_clusters), size=self.N)
        self.centroids = np.random.randn(num_clusters, self.D)
        self.fuzziness = fuzziness
        self.max_iter = max_iter
        self.tol = tol

    def update_membership(self):
        distances = np.linalg.norm(self.X[:, np.newaxis] - self.centroids, axis=2)
        distances = np.clip(distances, np.finfo(np.float64).eps, None)  # Avoid division by zero
        distances_expanded = distances[:, :, np.newaxis]
        distances_sum = distances[:, np.newaxis]
        self.membership = 1 / ((distances_expanded / distances_sum) ** (2 / (self.fuzziness - 1))).sum(axis=-1)

    def update_centroids(self):
        self.centroids = ((self.membership ** self.fuzziness)[:, :, np.newaxis] * self.X[:, np.newaxis]).sum(axis=0) / (self.membership ** self.fuzziness).sum(axis=0)[:, np.newaxis]

    def fit(self):
        prev_centroids = np.copy(self.centroids)
        for _ in range(self.max_iter):
            self.update_membership()
            self.update_centroids()
            if np.linalg.norm(self.centroids - prev_centroids) < self.tol:
                break
            prev_centroids = np.copy(self.centroids)
        return self.membership.argmax(axis=1)

    def compute_average_distance(self):
        distances = np.linalg.norm(self.X[:, np.newaxis] - self.centroids, axis=2)
        avg_distances = np.sum(distances * self.membership, axis=0) / np.sum(self.membership, axis=0)
        return avg_distances

# Example usage:
# Generate some sample data
np.random.seed(0)
N = 500
D = 2
X = np.random.randn(N, D)

# Perform C-means clustering with soft clustering
num_clusters = 3
fuzziness = 5
cmeans = CMeans(X, num_clusters, fuzziness)
labels = cmeans.fit()

# Calculate average distances
avg_distances = cmeans.compute_average_distance()
for i, distance in enumerate(avg_distances):
    print(f'Average distance to centroid {i}: {distance}')

# Plot the results
plt.scatter(X[:, 0], X[:, 1], c=labels, cmap='viridis', alpha=0.5)  # Use alpha to make points semi-transparent
plt.scatter(cmeans.centroids[:, 0], cmeans.centroids[:, 1], c='red', marker='x', label='Centroids')
plt.title('C-means Soft Clustering')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.show()

 # Plot average distances
plt.figure()
plt.bar(range(5), avg_distances)
plt.title('Average Distance in Each Cluster')
plt.xlabel('Cluster')
plt.ylabel('Average Distance')
plt.show()