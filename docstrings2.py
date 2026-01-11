def calculate_bmi(bm, ht):
    """
    Calculates Body Mass Index (BMI).

    PARAMETER:
        bm: body mass in kilograms
        ht: height in meters

    RETURN: BMI value (float)
    """
    if bm <= 0:
        raise ValueError("Weight must be positive")

    if ht <= 0:
        raise ValueError("Height must be positive")

    return round(bm / (ht ** 2), 2)


def check_hydration(bm, ht, act_lvl="mid"):
    """
    Computes hydration needs for the user/patient.

    PARAMETER:
        bm: body mass in kilograms
        ht: height in meters
        act_lvl: activity level, can be "low", "mid", or "high"

    RETURN: hydration in liters

    Notes:
    - DOES NOT ACCOUNT FOR FEVER OR HOT CLIMATE
    - This function is for DEMO ONLY DO NOT USE CLINICALLY

    """

    if bm <= 0:
        raise ValueError("Weight must be positive")

    if ht <= 0:
        raise ValueError("Height must be positive")

    base = 30 * bm / 1000

    if act_lvl == "low":
        factor = 0.9
    elif act_lvl == "mid":
        factor = 1.0
    elif act_lvl == "high":
        factor = 1.2
    else:
        factor = 1.0

    daily_liters = base * factor

    return round(daily_liters, 2)