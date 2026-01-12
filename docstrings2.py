def calculate_bmi(body_mass_kg, height_m):
    """
    Calculates Body Mass Index (BMI).

    PARAMETER:
        body_mass_kg: body mass in kilograms
        height_m: height in meters

    RETURN: BMI value (float)
    """
    if body_mass_kg <= 0:
        raise ValueError("Weight must be positive")

    if height_m <= 0:
        raise ValueError("Height must be positive")

    return round(body_mass_kg / (height_m ** 2), 2)


def check_hydration(body_mass_kg, height_m, activity_level="mid"):
    """
    Computes hydration needs for the user/patient.

    PARAMETER:
        body_mass_kg: body mass in kilograms
        height_m: height in meters
        activity_level: activity level, can be "low", "mid", or "high"

    RETURN: hydration in liters

    Notes:
    - DOES NOT ACCOUNT FOR FEVER OR HOT CLIMATE
    - This function is for DEMO ONLY DO NOT USE CLINICALLY

    """

    if body_mass_kg <= 0:
        raise ValueError("Weight must be positive")

    if height_m <= 0:
        raise ValueError("Height must be positive")

    base = 30 * body_mass_kg / 1000

    if activity_level == "low":
        factor = 0.9
    elif activity_level == "mid":
        factor = 1.0
    elif activity_level == "high":
        factor = 1.2
    else:
        factor = 1.0

    daily_liters = base * factor

    return round(daily_liters, 2)