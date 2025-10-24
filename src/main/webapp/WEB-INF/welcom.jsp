<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cinema4K - welcoooom</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --bg-primary: #0d1117;
            --bg-secondary: #161b22;
            --text-primary: #ffffff;
            --text-secondary: #8b949e;
            --accent-teal: #01d9aa;
            --accent-blue: #09abe0;
            --border-color: #30363d;
            --card-bg: #161b22;
        }

        html, body {
            width: 100%;
            height: 100%;
            font-family: 'Space Grotesk', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
            background: var(--bg-primary);
            color: var(--text-primary);
            overflow-x: hidden;
        }

        /* Background with gradient overlay */
        .background {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(135deg, #0d1117 0%, #161b22 50%, #0d1117 100%);
            z-index: -1;
        }

        .background::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: radial-gradient(circle at 20% 50%, rgba(1, 217, 170, 0.1) 0%, transparent 50%),
            radial-gradient(circle at 80% 80%, rgba(9, 171, 224, 0.1) 0%, transparent 50%);
            animation: float 20s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translate(0, 0); }
            50% { transform: translate(30px, -30px); }
        }

        /* Main container */
        .container {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            position: relative;
            z-index: 1;
        }

        /* Header */
        header {
            padding: 2rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid var(--border-color);
            backdrop-filter: blur(10px);
            background: rgba(13, 17, 23, 0.8);
        }

        .logo {
            font-size: 1.5rem;
            font-weight: 700;
            letter-spacing: 2px;
            background: linear-gradient(135deg, var(--accent-teal), var(--accent-blue));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .logo-dot {
            width: 12px;
            height: 12px;
            background: var(--accent-teal);
            border-radius: 50%;
            display: inline-block;
        }

        nav {
            display: flex;
            gap: 2rem;
            align-items: center;
        }

        nav a {
            color: var(--text-secondary);
            text-decoration: none;
            font-size: 0.95rem;
            transition: color 0.3s ease;
            position: relative;
        }

        nav a::after {
            content: '';
            position: absolute;
            bottom: -5px;
            left: 0;
            width: 0;
            height: 2px;
            background: linear-gradient(90deg, var(--accent-teal), var(--accent-blue));
            transition: width 0.3s ease;
        }

        nav a:hover {
            color: var(--text-primary);
        }

        nav a:hover::after {
            width: 100%;
        }

        /* Main content */
        .main-content {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 4rem 2rem;
        }

        .hero {
            max-width: 900px;
            text-align: center;
            opacity: 0;
            animation: fadeInUp 1s ease-out forwards;
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .hero h1 {
            font-size: clamp(2.5rem, 8vw, 4.5rem);
            font-weight: 700;
            margin-bottom: 1rem;
            line-height: 1.2;
            letter-spacing: -1px;
        }

        .hero .subtitle {
            font-size: clamp(1rem, 3vw, 1.5rem);
            color: var(--text-secondary);
            margin-bottom: 2rem;
            font-weight: 300;
            line-height: 1.6;
        }

        .cta-buttons {
            display: flex;
            gap: 1rem;
            justify-content: center;
            flex-wrap: wrap;
            margin-bottom: 4rem;
        }

        .btn {
            padding: 1rem 2rem;
            border: none;
            border-radius: 50px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            font-family: inherit;
            letter-spacing: 0.5px;
        }

        .btn-primary {
            background: linear-gradient(135deg, var(--accent-teal), var(--accent-blue));
            color: var(--bg-primary);
            box-shadow: 0 8px 25px rgba(1, 217, 170, 0.3);
        }

        .btn-primary:hover {
            transform: translateY(-3px);
            box-shadow: 0 12px 35px rgba(1, 217, 170, 0.4);
        }

        .btn-secondary {
            background: transparent;
            color: var(--text-primary);
            border: 2px solid var(--border-color);
        }

        .btn-secondary:hover {
            border-color: var(--accent-teal);
            color: var(--accent-teal);
            background: rgba(1, 217, 170, 0.1);
        }

        /* Stats section */
        .stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 2rem;
            margin-top: 4rem;
            padding-top: 4rem;
            border-top: 1px solid var(--border-color);
        }

        .stat-item {
            text-align: center;
            opacity: 0;
            animation: fadeInUp 1s ease-out forwards;
        }

        .stat-item:nth-child(1) { animation-delay: 0.2s; }
        .stat-item:nth-child(2) { animation-delay: 0.4s; }
        .stat-item:nth-child(3) { animation-delay: 0.6s; }

        .stat-number {
            font-size: 2.5rem;
            font-weight: 700;
            background: linear-gradient(135deg, var(--accent-teal), var(--accent-blue));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            margin-bottom: 0.5rem;
        }

        .stat-label {
            color: var(--text-secondary);
            font-size: 0.95rem;
        }

        /* Footer */
        footer {
            padding: 2rem;
            border-top: 1px solid var(--border-color);
            text-align: center;
            color: var(--text-secondary);
            font-size: 0.9rem;
            backdrop-filter: blur(10px);
            background: rgba(13, 17, 23, 0.8);
        }

        .social-links {
            display: flex;
            gap: 1.5rem;
            justify-content: center;
            margin-bottom: 1rem;
        }

        .social-links a {
            width: 40px;
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 2px solid var(--border-color);
            border-radius: 50%;
            color: var(--text-secondary);
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .social-links a:hover {
            border-color: var(--accent-teal);
            color: var(--accent-teal);
            background: rgba(1, 217, 170, 0.1);
            transform: translateY(-3px);
        }

        /* Responsive */
        @media (max-width: 768px) {
            header {
                flex-direction: column;
                gap: 1.5rem;
            }

            nav {
                flex-wrap: wrap;
                justify-content: center;
                gap: 1rem;
            }

            .hero h1 {
                font-size: 2rem;
            }

            .cta-buttons {
                flex-direction: column;
                align-items: center;
            }

            .btn {
                width: 100%;
                max-width: 300px;
            }

            .stats {
                grid-template-columns: 1fr;
                gap: 1.5rem;
            }
        }

        /* Scroll animations */
        .scroll-reveal {
            opacity: 0;
            transform: translateY(30px);
        }

        .scroll-reveal.active {
            opacity: 1;
            transform: translateY(0);
            transition: all 0.6s ease-out;
        }
    </style>
</head>
<body>
<div class="background"></div>

<div class="container">
    <!-- Header -->
    <header>
        <div class="logo">
            Cinema4k<span class="logo-dot"></span>
        </div>
        <nav>
            <a href="#about">About</a>
            <a href="#projects">Projects</a>
            <a href="#contact">Contact</a>
        </nav>
    </header>

    <!-- Main Content -->
    <div class="main-content">
        <div class="hero">
            <h1>I'm Mouad</h1>
            <p class="subtitle">Building beautiful digital experiences with modern design and cutting-edge technology</p>

            <div class="cta-buttons">
                <button class="btn btn-primary">Get In Touch</button>
                <button class="btn btn-secondary">View My Work</button>
            </div>

            <div class="stats">
                <div class="stat-item">
                    <div class="stat-number">2+</div>
                    <div class="stat-label">Years Experience</div>
                </div>
                <div class="stat-item">
                    <div class="stat-number">50+</div>
                    <div class="stat-label">Projects Completed</div>
                </div>
                <div class="stat-item">
                    <div class="stat-number">100%</div>
                    <div class="stat-label">Client Satisfaction</div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <div class="social-links">
            <a href="#" title="GitHub">GH</a>
            <a href="#" title="LinkedIn">LI</a>
            <a href="#" title="Twitter">TW</a>
            <a href="#" title="Email">@G</a>
        </div>
        <p>&copy; 2025 Mouad. All rights reserved.</p>
    </footer>
</div>

<script>
    // GSAP animations
    gsap.registerPlugin();

    // Animate hero on load
    gsap.timeline()
        .from('.hero', {
            duration: 0.8,
            opacity: 0,
            y: 30,
            ease: 'power2.out'
        })
        .from('.stat-item', {
            duration: 0.6,
            opacity: 0,
            y: 20,
            stagger: 0.15,
            ease: 'power2.out'
        }, '-=0.4');

    // Button hover animations
    document.querySelectorAll('.btn').forEach(btn => {
        btn.addEventListener('mouseenter', function() {
            gsap.to(this, {
                duration: 0.3,
                scale: 1.05,
                ease: 'power2.out'
            });
        });

        btn.addEventListener('mouseleave', function() {
            gsap.to(this, {
                duration: 0.3,
                scale: 1,
                ease: 'power2.out'
            });
        });
    });

    // Scroll reveal animation
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                gsap.to(entry.target, {
                    duration: 0.6,
                    opacity: 1,
                    y: 0,
                    ease: 'power2.out'
                });
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    document.querySelectorAll('.scroll-reveal').forEach(el => {
        observer.observe(el);
    });

    // Smooth scroll for navigation
    document.querySelectorAll('nav a').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                gsap.to(window, {
                    duration: 0.8,
                    scrollTo: target,
                    ease: 'power2.inOut'
                });
            }
        });
    });

    // Mouse follow effect on background
    document.addEventListener('mousemove', (e) => {
        const x = (e.clientX / window.innerWidth) * 100;
        const y = (e.clientY / window.innerHeight) * 100;

        gsap.to('.background::before', {
            duration: 0.5,
            backgroundPosition: `${x}% ${y}%`,
            ease: 'power1.out'
        });
    });
</script>
</body>
</html>