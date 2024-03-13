window.onload = function() {
    const startup = document.getElementById('startup');
    const mainContent = document.getElementById('main-content');

    function onScroll() {
        // Apply styles to fade out the startup section
        startup.style.opacity = 0;
        startup.style.visibility = 'hidden';
        // Set startup to absolute position after the transition to remove it from the flow
        setTimeout(() => {
            startup.style.position = 'absolute';
        }, 500); // Match this delay with the transition duration

        // Fade in the main content
        mainContent.style.opacity = 1;
        mainContent.style.visibility = 'visible';

        // Remove the scroll event listener
        window.removeEventListener('scroll', onScroll);
    }

    window.addEventListener('scroll', onScroll);
};

document.getElementById('program-select').addEventListener('change', function() {
    var courseSelect = document.getElementById('course-select');
    courseSelect.innerHTML = ''; // Clear previous options
    courseSelect.disabled = false; // Enable the course select

    if (this.value === 'program1') {
        // Populate with courses for program 1
        var courses = ['Course 1.1', 'Course 1.2', 'Course 1.3'];
    } else if (this.value === 'program2') {
        // Populate with courses for program 2
        var courses = ['Course 2.1', 'Course 2.2', 'Course 2.3'];
    } else {
        courseSelect.disabled = true; // Disable if no program is selected
        return;
    }

    // Add courses as options
    courses.forEach(function(course) {
        var option = document.createElement('option');
        option.value = course;
        option.textContent = course;
        courseSelect.appendChild(option);
    });
});

// Optional: Add submit event listener
document.getElementById('program-form').addEventListener('submit', function(e) {
    e.preventDefault();
    // Process the form submission here
    console.log('Program:', this.program.value);
    console.log('Course:', this.course.value);
});
