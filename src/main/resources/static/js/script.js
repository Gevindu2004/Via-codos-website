// Explore button functionality
function explore() {
    window.location.href = "/dashboard";
}

// Initialize all interactive effects when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    const title = document.querySelector('.welcome-title');
    const btn = document.querySelector('.explore-btn');
    
    // Add mouse move effect to title for 3D-like interaction
    document.addEventListener('mousemove', function(e) {
        const x = e.clientX / window.innerWidth;
        const y = e.clientY / window.innerHeight;
        
        title.style.transform = `translateY(${-20 + y * 10}px) rotateX(${y * 5}deg) rotateY(${x * 5}deg)`;
    });
    
    // Add click ripple effect to button
    btn.addEventListener('click', function(e) {
        const ripple = document.createElement('span');
        const rect = this.getBoundingClientRect();
        const size = Math.max(rect.width, rect.height);
        const x = e.clientX - rect.left - size / 2;
        const y = e.clientY - rect.top - size / 2;
        
        ripple.style.width = ripple.style.height = size + 'px';
        ripple.style.left = x + 'px';
        ripple.style.top = y + 'px';
        ripple.classList.add('ripple');
        
        this.appendChild(ripple);
        
        setTimeout(() => {
            ripple.remove();
        }, 600);
    });
    
    // Add keyboard navigation support
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Enter' || e.key === ' ') {
            e.preventDefault();
            explore();
        }
    });
    
    // Add touch support for mobile devices
    btn.addEventListener('touchstart', function(e) {
        this.style.transform = 'translateY(-1px)';
    });
    
    btn.addEventListener('touchend', function(e) {
        this.style.transform = '';
    });
}); 