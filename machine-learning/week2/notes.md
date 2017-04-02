### Gradient Descent in Practice
#### Feature Scaling
- Idea: make sure features are on a similar scale.
  - feature scaling: 
    - dividing the input values by the range 
    - (i.e. the maximum value minus the minimum value) of the input variable
    - resulting in a new range of just 1.
  - mean normalization: 
    - subtracting the average value for an input variable from the values for that input variable 
    - resulting in a new average value for the input variable of just zero. 
#### Learning Rate
- Debugging gradient descent.
  - Make a plot with number of iterations on the x-axis. 
  - Now plot the cost function, J(θ) over the number of iterations of gradient descent. 
  - If J(θ) ever increases, then you probably need to decrease α.
- Automatic convergence test. 
  - Declare convergence if J(θ) decreases by less than E in one iteration, where E is some small value such as 10−3. 
  - However in practice it's difficult to choose this threshold value.
- Learning rate α:
  - If α is too small: slow convergence.
  - If α is too large: ￼may not decrease on every iteration and thus may not converge.
### Features and Polynomial Regression
#### Polynomial Regression
- We can change the behavior or curve of our hypothesis function by making it a quadratic, cubic or square root function (or any other form).
