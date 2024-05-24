import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import json

def generate_route_map(filename='route_map.json'):
    num_cities = np.random.randint(25, 36)
    route_map = {}
    for i in range(num_cities):
        distances = {}
        for j in range(num_cities):
            if i != j:
                distances[j] = np.random.randint(10, 101)
        route_map[i] = distances
    
    with open(filename, 'w') as file:
        json.dump(route_map, file)
    
    return route_map

def load_route_map(filename='route_map.json'):
    with open(filename, 'r') as file:
        return json.load(file)
    
def spacing(point1, point2):
    return np.sqrt(np.sum((point1 - point2)**2))

def ant_colony_optimization(points, n_ants, n_iterations, alpha, beta, evaporation_rate, Q):
    n_points = len(points)
    pheromone = np.ones((n_points, n_points))
    best_path = None
    best_path_length = np.inf
    
    for iteration in range(n_iterations):
        paths = []
        path_lengths = []
        
        for ant in range(n_ants):
            visited = [False]*n_points
            current_point = np.random.randint(n_points)
            visited[current_point] = True
            path = [current_point]
            path_length = 0
            
            while False in visited:
                unvisited = np.where(np.logical_not(visited))[0]
                probabilities = np.zeros(len(unvisited))
                
                for i, unvisited_point in enumerate(unvisited):
                    probabilities[i] = pheromone[current_point, unvisited_point]**alpha / spacing(points[current_point], points[unvisited_point])**beta
                
                probabilities /= np.sum(probabilities)
                
                next_point = np.random.choice(unvisited, p=probabilities)
                path.append(next_point)
                path_length += spacing(points[current_point], points[next_point])
                visited[next_point] = True
                current_point = next_point
            
            paths.append(path)
            path_lengths.append(path_length)
            
            if path_length < best_path_length:
                best_path = path
                best_path_length = path_length
        
        pheromone *= evaporation_rate
        
        for path, path_length in zip(paths, path_lengths):
            for i in range(n_points-1):
                pheromone[path[i], path[i+1]] += Q/path_length
            pheromone[path[-1], path[0]] += Q/path_length
    
    plt.figure(figsize=(8, 6))
    plt.scatter(points[:,0], points[:,1], c='r', marker='o', label='Cities')
    
    for i in range(n_points-1):
        plt.plot([points[best_path[i],0], points[best_path[i+1],0]],
                 [points[best_path[i],1], points[best_path[i+1],1]],
                 c='g', linestyle='-', linewidth=2, marker='o', label=f'City {i+1} ')
        
    plt.plot([points[best_path[0],0], points[best_path[-1],0]],
             [points[best_path[0],1], points[best_path[-1],1]],
             c='g', linestyle='-', linewidth=2, marker='o')
    
    for i, city in enumerate(points):
        plt.text(city[0], city[1], str(i), fontsize=9, ha='center', va='bottom')
    
    plt.xlabel('X Label')
    plt.ylabel('Y Label')
    plt.title('Ant Colony Optimization - TSP')
    plt.grid(True)
    plt.legend()
    plt.show()

# Example usage:
points = np.random.rand(np.random.randint(25, 36), 2) 
for i in range(10):
    ant_colony_optimization(points, n_ants=15+2*1, n_iterations=100, alpha=1+i, beta=1+i, evaporation_rate=0.5, Q=1)
